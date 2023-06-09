package org.jsoup.nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.DebugKt;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class FormElement extends Element {
    private final Elements elements = new Elements();

    public FormElement(Tag tag, String str, Attributes attributes) {
        super(tag, str, attributes);
    }

    public Elements elements() {
        return this.elements;
    }

    public FormElement addElement(Element element) {
        this.elements.add(element);
        return this;
    }

    /* access modifiers changed from: protected */
    public void removeChild(Node node) {
        super.removeChild(node);
        this.elements.remove(node);
    }

    public Connection submit() {
        String absUrl = hasAttr("action") ? absUrl("action") : baseUri();
        Validate.notEmpty(absUrl, "Could not determine a form action URL for submit. Ensure you set a base URI when parsing.");
        Connection.Method method = attr("method").equalsIgnoreCase("POST") ? Connection.Method.POST : Connection.Method.GET;
        Document ownerDocument = ownerDocument();
        return (ownerDocument != null ? ownerDocument.connection().newRequest() : Jsoup.newSession()).url(absUrl).data((Collection<Connection.KeyVal>) formData()).method(method);
    }

    public List<Connection.KeyVal> formData() {
        Element selectFirst;
        ArrayList arrayList = new ArrayList();
        Iterator it = this.elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            if (element.tag().isFormSubmittable() && !element.hasAttr("disabled")) {
                String attr = element.attr("name");
                if (attr.length() != 0) {
                    String attr2 = element.attr(SVGParser.XML_STYLESHEET_ATTR_TYPE);
                    if (!attr2.equalsIgnoreCase("button")) {
                        if ("select".equals(element.normalName())) {
                            boolean z = false;
                            Iterator it2 = element.select("option[selected]").iterator();
                            while (it2.hasNext()) {
                                arrayList.add(HttpConnection.KeyVal.create(attr, ((Element) it2.next()).val()));
                                z = true;
                            }
                            if (!z && (selectFirst = element.selectFirst("option")) != null) {
                                arrayList.add(HttpConnection.KeyVal.create(attr, selectFirst.val()));
                            }
                        } else if (!"checkbox".equalsIgnoreCase(attr2) && !"radio".equalsIgnoreCase(attr2)) {
                            arrayList.add(HttpConnection.KeyVal.create(attr, element.val()));
                        } else if (element.hasAttr("checked")) {
                            arrayList.add(HttpConnection.KeyVal.create(attr, element.val().length() > 0 ? element.val() : DebugKt.DEBUG_PROPERTY_VALUE_ON));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public FormElement clone() {
        return (FormElement) super.clone();
    }
}
