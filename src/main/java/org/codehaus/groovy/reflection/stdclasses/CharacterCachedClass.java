/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.codehaus.groovy.reflection.stdclasses;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class CharacterCachedClass extends CachedClass {
    private final boolean allowNull;

    public CharacterCachedClass(Class klazz, ClassInfo classInfo, boolean allowNull) {
        super(klazz, classInfo);
        this.allowNull = allowNull;
    }

    @Override
    public boolean isDirectlyAssignable(Object argument) {
        return (allowNull && argument == null) || argument instanceof Character;
    }

    @Override
    public boolean isAssignableFrom(Class classToTransformFrom) {
        return (allowNull && classToTransformFrom == null)
                || classToTransformFrom == Character.class
                || classToTransformFrom == Character.TYPE;
    }
}
