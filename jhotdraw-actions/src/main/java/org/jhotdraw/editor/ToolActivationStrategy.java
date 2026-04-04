/*
 * @(#)ToolActivationStrategy.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.editor;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.tool.Tool;

/** Strategy for activating and deactivating tools in a drawing editor. */
public interface ToolActivationStrategy {

  void activate(Tool tool, DrawingEditor editor);

  void deactivate(Tool tool, DrawingEditor editor);

  default void onViewAdded(Tool tool, DrawingEditor editor, DrawingView view) {}

  default void onViewRemoved(Tool tool, DrawingEditor editor, DrawingView view) {}

  default void onActiveViewChanged(
      Tool tool, DrawingEditor editor, DrawingView oldView, DrawingView newView) {}
}
