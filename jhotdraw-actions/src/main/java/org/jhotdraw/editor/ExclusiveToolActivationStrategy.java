/*
 * @(#)ExclusiveToolActivationStrategy.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.editor;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.tool.Tool;

/**
 * Activates the tool only on the active view and rebinds listeners when the active view changes.
 */
public class ExclusiveToolActivationStrategy extends DefaultToolActivationStrategy {

  @Override
  public void activate(Tool tool, DrawingEditor editor) {
    DefaultDrawingEditor drawingEditor = asDefaultEditor(editor);
    tool.activate(editor);
    drawingEditor.addToolListener(tool);
    DrawingView activeView = editor.getActiveView();
    if (activeView != null) {
      drawingEditor.registerToolOnView(tool, activeView);
    }
  }

  @Override
  public void onViewRemoved(Tool tool, DrawingEditor editor, DrawingView view) {
    asDefaultEditor(editor).unregisterToolFromView(tool, view);
  }

  @Override
  public void onActiveViewChanged(
      Tool tool, DrawingEditor editor, DrawingView oldView, DrawingView newView) {
    DefaultDrawingEditor drawingEditor = asDefaultEditor(editor);
    if (oldView != null) {
      drawingEditor.unregisterToolFromView(tool, oldView);
    }
    if (newView != null) {
      drawingEditor.registerToolOnView(tool, newView);
    }
  }
}
