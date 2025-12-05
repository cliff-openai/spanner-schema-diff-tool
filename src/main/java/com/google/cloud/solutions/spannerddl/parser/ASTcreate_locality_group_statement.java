/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.solutions.spannerddl.parser;

import static com.google.cloud.solutions.spannerddl.diff.AstTreeUtils.getChildByType;
import static com.google.cloud.solutions.spannerddl.diff.AstTreeUtils.getOptionalChildByType;

import com.google.cloud.solutions.spannerddl.diff.AstTreeUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;

public class ASTcreate_locality_group_statement extends SimpleNode {

  public ASTcreate_locality_group_statement(int id) {
    super(id);
  }

  public ASTcreate_locality_group_statement(DdlParser p, int id) {
    super(p, id);
  }

  public String getName() {
    return AstTreeUtils.tokensToString(getChildByType(children, ASTname.class), false);
  }

  public ASToptions_clause getOptionsClause() {
    return getOptionalChildByType(children, ASToptions_clause.class);
  }

  private void validateChildren() {
    AstTreeUtils.validateChildrenClasses(
        children, ImmutableSet.of(ASTname.class, ASToptions_clause.class));
  }

  @Override
  public String toString() {
    validateChildren();
    return Joiner.on(" ")
        .skipNulls()
        .join(
            "CREATE",
            "LOCALITY",
            "GROUP",
            getName(),
            getOptionsClause());
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof ASTcreate_locality_group_statement) && toString().equals(obj.toString());
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
