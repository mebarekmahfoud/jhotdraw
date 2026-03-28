/*
 * @(#)SerializationInputOutputFormat.java
 *
 * Copyright (c) 2009-2010 The authors and contributors of JHotDraw.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.io;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.io.InputFormat;

/**
 * {@code SerializationInputOutputFormat} uses Java Serialization for reading and and writing {@code
 * Drawing} objects.
 */
public class SerializationInputFormat implements InputFormat {

  /** Format description used for the file filter. */
  private String description;

  /** File name extension used for the file filter. */
  private String fileExtension;

  /** Image IO image format name. */
  private String formatName;

  /** The mime type is used for clipboard access. */
  private String mimeType;

  /** The data flavor constructed from the mime type. */
  private DataFlavor dataFlavor;

  private Drawing prototype;

  /**
   * Creates a new instance with format name "Drawing", file extension "xml" and mime type
   * "image/x-jhotdraw".
   */
  public SerializationInputFormat() {
    this("Drawing", "ser", new DefaultDrawing());
  }

  /** Creates a new instance using the specified parameters. */
  public SerializationInputFormat(String description, String fileExtension, Drawing prototype) {
    this.description = description;
    this.fileExtension = fileExtension;
    this.mimeType = DataFlavor.javaSerializedObjectMimeType;
    this.prototype = prototype;
    this.dataFlavor = new DataFlavor(prototype.getClass(), description);
  }

  @Override
  public FileFilter getFileFilter() {
    return new FileNameExtensionFilter(description, fileExtension);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void read(InputStream in, Drawing drawing, boolean replace) throws IOException {
    if (in == null) {
      throw new IllegalArgumentException("Input stream must not be null");
    }
    if (drawing == null) {
      throw new IllegalArgumentException("Drawing must not be null");
    }

    try {
      ObjectInputStream oin = new ObjectInputStream(in);
      Drawing deserializedDrawing = (Drawing) oin.readObject();
      mergeDrawing(deserializedDrawing, drawing, replace);
    } catch (ClassNotFoundException ex) {
      IOException ioe = new IOException("Couldn't read drawing.");
      ioe.initCause(ex);
      throw ioe;
    } catch (ClassCastException ex) {
      IOException ioe = new IOException("Input stream does not contain a serialized Drawing.");
      ioe.initCause(ex);
      throw ioe;
    }
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return flavor.equals(dataFlavor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void read(Transferable t, Drawing drawing, boolean replace)
      throws UnsupportedFlavorException, IOException {
    if (t == null) {
      throw new IllegalArgumentException("Transferable must not be null");
    }
    if (drawing == null) {
      throw new IllegalArgumentException("Drawing must not be null");
    }

    try {
      Drawing transferableDrawing = (Drawing) t.getTransferData(dataFlavor);
      mergeDrawing(transferableDrawing, drawing, replace);
    } catch (ClassCastException ex) {
      IOException ioe =
          new IOException("Transferable does not contain a Drawing for the expected DataFlavor.");
      ioe.initCause(ex);
      throw ioe;
    }
  }

  @SuppressWarnings("unchecked")
  private void mergeDrawing(Drawing sourceDrawing, Drawing targetDrawing, boolean replace) {
    if (sourceDrawing == null) {
      throw new IllegalArgumentException("Source drawing must not be null");
    }
    if (replace) {
      for (Map.Entry<AttributeKey<?>, Object> e :
          sourceDrawing.attr().getAttributes().entrySet()) {
        targetDrawing.attr().set((AttributeKey<Object>) e.getKey(), e.getValue());
      }
    }
    for (Figure f : sourceDrawing.getChildren()) {
      targetDrawing.add(f);
    }
  }
}
