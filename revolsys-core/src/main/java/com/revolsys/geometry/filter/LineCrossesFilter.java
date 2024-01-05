/*
 * $URL:$
 * $Author:$
 * $Date:$
 * $Revision:$

 * Copyright 2004-2007 Revolution Systems Inc.
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
package com.revolsys.geometry.filter;

import java.util.function.Predicate;

import com.revolsys.geometry.model.LineString;

public class LineCrossesFilter implements Predicate<LineString> {

  /** The geometry to compare the data objects to to. */
  private final LineString geometry;

  /**
   * Construct a new LineStringLessThanDistanceFilter.
   *
   * @param geometry The geometry to compare the data objects to to.
   * @param maxDistance
   */
  public LineCrossesFilter(final LineString geometry) {
    this.geometry = geometry;
  }

  @Override
  public boolean test(final LineString geometry) {
    return geometry.crosses(this.geometry);
  }

}
