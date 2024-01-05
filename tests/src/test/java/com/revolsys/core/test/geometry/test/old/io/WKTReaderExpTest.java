package com.revolsys.core.test.geometry.test.old.io;

import java.io.IOException;

import com.revolsys.geometry.model.Geometry;
import com.revolsys.geometry.model.GeometryFactory;
import com.revolsys.geometry.model.Point;
import com.revolsys.geometry.wkb.ParseException;
import com.revolsys.geometry.wkb.WKTReader;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * Tests the {@link WKTReader} with exponential notation.
 */
public class WKTReaderExpTest extends TestCase {
  public static void main(final String args[]) {
    TestRunner.run(WKTReaderExpTest.class);
  }

  private final GeometryFactory fact = GeometryFactory.DEFAULT_3D;

  private final WKTReader rdr = new WKTReader(this.fact);

  public WKTReaderExpTest(final String name) {
    super(name);
  }

  private void readBad(final String wkt) throws IOException {
    boolean threwParseEx = false;
    try {
      final Geometry g = this.rdr.read(wkt);
    } catch (final ParseException ex) {
      // System.out.println(ex.getMessage());
      threwParseEx = true;
    }
    assertTrue(threwParseEx);
  }

  private void readGoodCheckCoordinate(final String wkt, final double x, final double y)
    throws IOException, ParseException {
    final Geometry g = this.rdr.read(wkt);
    final Point pt = g.getPoint();
    assertEquals(pt.getX(), x, 0.0001);
    assertEquals(pt.getY(), y, 0.0001);
  }

  public void testBadExpFormat() throws IOException, ParseException {
    readBad("POINT (1e0a1 1X02)");
  }

  public void testBadExpPlusSign() throws IOException, ParseException {
    readBad("POINT (1e+01 1X02)");
  }

  public void testBadPlusSign() throws IOException, ParseException {
    readBad("POINT ( +1e+01 1X02)");
  }

  public void testGoodBasicExp() throws IOException, ParseException {
    readGoodCheckCoordinate("POINT ( 1e01 -1E02)", 1E01, -1E02);
  }

  public void testGoodWithExpSign() throws IOException, ParseException {
    readGoodCheckCoordinate("POINT ( 1e-04 1E-05)", 1e-04, 1e-05);
  }
}
