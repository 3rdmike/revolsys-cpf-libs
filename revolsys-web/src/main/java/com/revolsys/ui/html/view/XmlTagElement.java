/*
 * Copyright 2004-2005 Revolution Systems Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.revolsys.ui.html.view;

import javax.xml.namespace.QName;

import com.revolsys.record.io.format.xml.XmlWriter;
import com.revolsys.util.HtmlUtil;

/**
 * @author paustin
 * @version 1.0
 */
public class XmlTagElement extends Element {
  private String content;

  private final QName tag;

  public XmlTagElement(final QName tag) {
    this.tag = tag;
  }

  public XmlTagElement(final QName tag, final String content) {
    this.tag = tag;
    this.content = content;
  }

  @Override
  public void serializeElement(final XmlWriter out) {
    HtmlUtil.serializeTag(out, this.tag, this.content);
  }
}
