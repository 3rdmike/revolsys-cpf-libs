/*
 * The JTS Topology Suite is a collection of Java classes that
 * implement the fundamental operations required to validate a given
 * geo-spatial data set to a known topological specification.
 *
 * Copyright (C) 2001 Vivid Solutions
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * For more information, contact:
 *
 *     Vivid Solutions
 *     Suite #1A
 *     2328 Government Street
 *     Victoria BC  V8T 5G5
 *     Canada
 *
 *     (250)385-6040
 *     www.vividsolutions.com
 */
package com.revolsys.core.test.geometry.test.old.perf.operation.buffer;

import com.revolsys.geometry.model.Geometry;
import com.revolsys.geometry.model.GeometryFactory;
import com.revolsys.geometry.util.Stopwatch;

/**
 * Test repeated buffering of a given input shape.
 * Intended to test the robustness of buffering.
 * Repeated buffering tends to generate challenging
 * somewhat pathological linework, which stresses the buffer algorithm.
 *
 * @version 1.7
 */
public class IteratedBufferStressTest {

  public static void main(final String args[]) {
    try {
      new IteratedBufferStressTest().run();
    } catch (final Exception ex) {
      ex.printStackTrace();
    }

  }

  private final GeometryFactory geometryFactory = GeometryFactory.floating2d(0);

  String inputWKT = "POLYGON ((110 320, 190 220, 60 200, 180 120, 120 40, 290 150, 410 40, 410 230, 500 340, 320 310, 260 370, 220 310, 110 320), (220 260, 250 180, 290 220, 360 150, 350 250, 260 280, 220 260))";

  public IteratedBufferStressTest() {
  }

  Geometry doBuffer(final Geometry g, final double dist) {
    // System.out.println("Buffering with dist = " + dist);
    final Geometry buf = g.buffer(dist);
    // System.out.println("Buffer result has " + buf.getVertexCount()
    // + " vertices");

    // System.out.println(buf);
    return buf;

  }

  void run() throws Exception {
    final Stopwatch totalSW = new Stopwatch();
    Geometry base = this.geometryFactory.geometry(this.inputWKT);
    double dist = 1.0;
    while (true) {
      final Geometry b1 = doBuffer(base, dist);
      final Geometry b2 = doBuffer(b1, -dist);
      dist += 1;
      base = b2;
      // System.out.println("---------------------- " +
      // totalSW.getTimeString());
      // System.out.println();
    }
  }
}
