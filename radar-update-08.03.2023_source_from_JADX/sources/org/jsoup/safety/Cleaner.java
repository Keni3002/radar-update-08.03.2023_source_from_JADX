package org.jsoup.safety;

import java.util.Collection;
import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class Cleaner {
    /* access modifiers changed from: private */
    public final Safelist safelist;

    public Cleaner(Safelist safelist2) {
        Validate.notNull(safelist2);
        this.safelist = safelist2;
    }

    public Document clean(Document document) {
        Validate.notNull(document);
        Document createShell = Document.createShell(document.baseUri());
        copySafeNodes(document.body(), createShell.body());
        createShell.outputSettings(document.outputSettings().clone());
        return createShell;
    }

    public boolean isValid(Document document) {
        Validate.notNull(document);
        return copySafeNodes(document.body(), Document.createShell(document.baseUri()).body()) == 0 && document.head().childNodes().isEmpty();
    }

    public boolean isValidBodyHtml(String str) {
        Document createShell = Document.createShell("");
        Document createShell2 = Document.createShell("");
        ParseErrorList tracking = ParseErrorList.tracking(1);
        createShell2.body().insertChildren(0, (Collection<? extends Node>) Parser.parseFragment(str, createShell2.body(), "", tracking));
        if (copySafeNodes(createShell2.body(), createShell.body()) != 0 || !tracking.isEmpty()) {
            return false;
        }
        return true;
    }

    private final class CleaningVisitor implements NodeVisitor {
        private Element destination;
        /* access modifiers changed from: private */
        public int numDiscarded;
        private final Element root;

        private CleaningVisitor(Element element, Element element2) {
            this.numDiscarded = 0;
            this.root = element;
            this.destination = element2;
        }

        public void head(Node node, int i) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (Cleaner.this.safelist.isSafeTag(element.normalName())) {
                    ElementMeta access$100 = Cleaner.this.createSafeElement(element);
                    Element element2 = access$100.f373el;
                    this.destination.appendChild(element2);
                    this.numDiscarded += access$100.numAttribsDiscarded;
                    this.destination = element2;
                } else if (node != this.root) {
                    this.numDiscarded++;
                }
            } else if (node instanceof TextNode) {
                this.destination.appendChild(new TextNode(((TextNode) node).getWholeText()));
            } else if (!(node instanceof DataNode) || !Cleaner.this.safelist.isSafeTag(node.parent().nodeName())) {
                this.numDiscarded++;
            } else {
                this.destination.appendChild(new DataNode(((DataNode) node).getWholeData()));
            }
        }

        public void tail(Node node, int i) {
            if ((node instanceof Element) && Cleaner.this.safelist.isSafeTag(node.nodeName())) {
                this.destination = this.destination.parent();
            }
        }
    }

    private int copySafeNodes(Element element, Element element2) {
        CleaningVisitor cleaningVisitor = new CleaningVisitor(element, element2);
        NodeTraversor.traverse((NodeVisitor) cleaningVisitor, (Node) element);
        return cleaningVisitor.numDiscarded;
    }

    /* access modifiers changed from: private */
    public ElementMeta createSafeElement(Element element) {
        String tagName = element.tagName();
        Attributes attributes = new Attributes();
        Element element2 = new Element(Tag.valueOf(tagName), element.baseUri(), attributes);
        Iterator<Attribute> it = element.attributes().iterator();
        int i = 0;
        while (it.hasNext()) {
            Attribute next = it.next();
            if (this.safelist.isSafeAttribute(tagName, element, next)) {
                attributes.put(next);
            } else {
                i++;
            }
        }
        attributes.addAll(this.safelist.getEnforcedAttributes(tagName));
        if (element.sourceRange().isTracked()) {
            element.sourceRange().track(element2, true);
        }
        if (element.endSourceRange().isTracked()) {
            element.endSourceRange().track(element2, false);
        }
        return new ElementMeta(element2, i);
    }

    private static class ElementMeta {

        /* renamed from: el */
        Element f373el;
        int numAttribsDiscarded;

        ElementMeta(Element element, int i) {
            this.f373el = element;
            this.numAttribsDiscarded = i;
        }
    }
}
