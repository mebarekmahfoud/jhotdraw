/*
 * @(#)ODGShapeFactory.java
 *
 * Copyright (c) 2007-2008 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.odg.io;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.samples.odg.figures.ODGBezierFigure;
import org.jhotdraw.samples.odg.figures.ODGEllipseFigure;
import org.jhotdraw.samples.odg.figures.ODGFigure;
import org.jhotdraw.samples.odg.figures.ODGPathFigure;
import org.jhotdraw.samples.odg.figures.ODGRectFigure;
import org.jhotdraw.utils.geom.path.BezierPath;

/**
 * Creates ODG geometric figures from parsed ODG data.
 */
class ODGShapeFactory {

  ODGFigure createRect(Rectangle2D.Double bounds, Map<AttributeKey<?>, Object> attributes) {
    ODGRectFigure figure = new ODGRectFigure();
    figure.setBounds(bounds);
    figure.attr().setAttributes(attributes);
    return figure;
  }

  ODGFigure createEllipse(Rectangle2D.Double bounds, Map<AttributeKey<?>, Object> attributes) {
    ODGEllipseFigure figure = new ODGEllipseFigure();
    figure.setBounds(bounds);
    figure.attr().setAttributes(attributes);
    return figure;
  }

  ODGFigure createLine(
      Point2D.Double start, Point2D.Double end, Map<AttributeKey<?>, Object> attributes) {
    ODGPathFigure figure = new ODGPathFigure();
    figure.setBounds(start, end);
    figure.attr().setAttributes(attributes);
    return figure;
  }

  ODGFigure createPolyline(Point2D.Double[] points, Map<AttributeKey<?>, Object> attributes) {
    ODGPathFigure figure = new ODGPathFigure();
    ODGBezierFigure bezier = new ODGBezierFigure();
    for (Point2D.Double point : points) {
      bezier.addNode(new BezierPath.Node(point.x, point.y));
    }
    figure.removeAllChildren();
    figure.add(bezier);
    figure.attr().setAttributes(attributes);
    return figure;
  }

  ODGFigure createPolygon(Point2D.Double[] points, Map<AttributeKey<?>, Object> attributes) {
    ODGPathFigure figure = new ODGPathFigure();
    ODGBezierFigure bezier = new ODGBezierFigure();
    for (Point2D.Double point : points) {
      bezier.addNode(new BezierPath.Node(point.x, point.y));
    }
    bezier.setClosed(true);
    figure.removeAllChildren();
    figure.add(bezier);
    figure.attr().setAttributes(attributes);
    return figure;
  }

  ODGFigure createPath(BezierPath[] paths, Map<AttributeKey<?>, Object> attributes) {
    ODGPathFigure figure = new ODGPathFigure();
    figure.removeAllChildren();
    for (BezierPath path : paths) {
      ODGBezierFigure bezier = new ODGBezierFigure();
      bezier.setBezierPath(path);
      figure.add(bezier);
    }
    figure.attr().setAttributes(attributes);
    return figure;
  }
}
