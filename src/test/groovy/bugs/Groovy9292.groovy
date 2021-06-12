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
package groovy.bugs

import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail

final class Groovy9292 {

    private final GroovyShell shell = new GroovyShell()

    @Test
    void 'test accessing a private super class field inside a closure - same module'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                class B extends A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - same package'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                assert true
            '''

            shell.evaluate '''
                package a

                class B extends A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - diff package'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                assert true
            '''

            shell.evaluate '''
                package b

                class B extends a.A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - same package, it qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                class B extends A {
                    @groovy.transform.CompileStatic
                    def test() {
                        with {
                            return it.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - diff package, it qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                assert true
            '''

            shell.evaluate '''
                package b

                class B extends a.A {
                    @groovy.transform.CompileStatic
                    def test() {
                        with {
                            return it.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - same package, this qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                class B extends A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return this.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - diff package, this qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                assert true
            '''

            shell.evaluate '''
                package b

                class B extends a.A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return this.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - same package, owner qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                class B extends A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return owner.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - diff package, owner qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                assert true
            '''

            shell.evaluate '''
                package b

                class B extends a.A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return owner.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - same package, delegate qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                class B extends A {
                    @groovy.transform.CompileStatic
                    def test() {
                        with {
                            return delegate.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - diff package, delegate qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                assert true
            '''

            shell.evaluate '''
                package b

                class B extends a.A {
                    @groovy.transform.CompileStatic
                    def test() {
                        with {
                            return delegate.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - same package, thisObject qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                class B extends A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return thisObject.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }

    @Test
    void 'test accessing a private super class field inside a closure - diff package, thisObject qualifier'() {
        shouldFail(MissingPropertyException) {
            shell.evaluate '''
                package a

                class A {
                    private String superField
                }

                assert true
            '''

            shell.evaluate '''
                package b

                class B extends a.A {
                    @groovy.transform.CompileStatic
                    def test() {
                        'something'.with {
                            return thisObject.superField
                        }
                    }
                }

                new B().test()
            '''
        }
    }
}
