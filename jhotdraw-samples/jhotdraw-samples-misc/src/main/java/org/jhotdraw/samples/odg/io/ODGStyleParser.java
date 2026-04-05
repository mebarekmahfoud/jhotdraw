/*
 * @(#)ODGStyleParser.java
 *
 * Copyright (c) 2007-2008 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.odg.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.jhotdraw.draw.AttributeKey;
import org.w3c.dom.Element;

/**
 * Encapsulates style parsing and style resolution for ODG documents.
 */
class ODGStyleParser {

  private final ODGStylesReader stylesReader;

  ODGStyleParser() {
    this(new ODGStylesReader());
  }

  ODGStyleParser(ODGStylesReader stylesReader) {
    this.stylesReader = stylesReader;
  }

  void parseStyles(InputStream inputStream) throws IOException {
    stylesReader.read(inputStream);
  }

  void parseStyles(Element rootElement) throws IOException {
    stylesReader.read(rootElement);
  }

  Map<AttributeKey<?>, Object> resolveStyle(String styleName) {
    return resolveStyle(styleName, "graphic");
  }

  Map<AttributeKey<?>, Object> resolveStyle(String styleName, String familyName) {
    return new HashMap<AttributeKey<?>, Object>(stylesReader.getAttributes(styleName, familyName));
  }
}
