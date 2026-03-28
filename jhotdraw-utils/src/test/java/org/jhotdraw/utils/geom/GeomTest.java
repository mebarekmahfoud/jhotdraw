/*
 * Copyright (C) 2026 JHotDraw.
 */
package org.jhotdraw.utils.geom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import java.awt.geom.Point2D;
import org.junit.jupiter.api.Test;

class GeomTest {

  @Test
  void length2ShouldReturnZeroForSamePoint() {
    assertEquals(0.0, Geom.length2(5.0, 5.0, 5.0, 5.0), 1e-9);
  }

  @Test
  void length2ShouldReturnSquaredDistance() {
    assertEquals(25.0, Geom.length2(0.0, 0.0, 3.0, 4.0), 1e-9);
  }

  @Test
  void lineContainsPointShouldDetectPointOnHorizontalLine() {
    assertTrue(Geom.lineContainsPoint(0, 0, 10, 0, 5, 0, 1.0));
  }

  @Test
  void lineContainsPointShouldRejectPointOutsideTolerance() {
    assertFalse(Geom.lineContainsPoint(0, 0, 10, 0, 5, 3, 1.0));
  }

  @Test
  void lineContainsPointShouldDetectPointOnVerticalLine() {
    assertTrue(Geom.lineContainsPoint(2, 0, 2, 10, 2, 7, 0.5));
  }

  @Test
  void angleShouldBeZeroForHorizontalRightVector() {
    assertEquals(0.0, Geom.angle(0.0, 0.0, 5.0, 0.0), 1e-9);
  }

  @Test
  void angleShouldBePiOverTwoForVerticalUpVector() {
    assertEquals(Math.PI / 2.0, Geom.angle(0.0, 0.0, 0.0, 5.0), 1e-9);
  }

  @Test
  void intersectShouldReturnIntersectionForCrossingSegments() {
    Point intersection = Geom.intersect(0, 0, 10, 10, 0, 10, 10, 0);
    assertNotNull(intersection);
    assertEquals(new Point(5, 5), intersection);
  }

  @Test
  void intersectShouldReturnNullForParallelSegments() {
    Point2D.Double intersection = Geom.intersect(0.0, 0.0, 10.0, 0.0, 0.0, 5.0, 10.0, 5.0);
    assertNull(intersection);
  }
}
