/*
 * @(#)DefaultToolActivationStrategy.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.editor;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.tool.Tool;

/** Preserves the historical behavior by registering the tool on all views. */
public class DefaultToolActivationStrategy implements ToolActivationStrategy {

  @Override
  public void activate(Tool tool, DrawingEditor editor) {
    DefaultDrawingEditor drawingEditor = asDefaultEditor(editor);
    tool.activate(editor);
    drawingEditor.registerToolOnAllViews(tool);
    drawingEditor.addToolListener(tool);
  }

  @Override
  public void deactivate(Tool tool, DrawingEditor editor) {
    DefaultDrawingEditor drawingEditor = asDefaultEditor(editor);
    drawingEditor.unregisterToolFromAllViews(tool);
    tool.deactivate(editor);
    drawingEditor.removeToolListener(tool);
  }

  @Override
  public void onViewAdded(Tool tool, DrawingEditor editor, DrawingView view) {
    asDefaultEditor(editor).registerToolOnView(tool, view);
  }

  @Override
  public void onViewRemoved(Tool tool, DrawingEditor editor, DrawingView view) {
    asDefaultEditor(editor).unregisterToolFromView(tool, view);
  }

  protected DefaultDrawingEditor asDefaultEditor(DrawingEditor editor) {
    if (editor instanceof DefaultDrawingEditor drawingEditor) {
      return drawingEditor;
    }
    throw new IllegalArgumentException(
        "DefaultToolActivationStrategy requires DefaultDrawingEditor");
  }
}
