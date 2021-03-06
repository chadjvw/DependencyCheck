/*
 * This file is part of dependency-check-core.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2016 Jeremy Long. All Rights Reserved.
 */
package org.owasp.dependencycheck.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jeremy
 */
public class ExpectedOjectInputStreamTest {

    public ExpectedOjectInputStreamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resolveClass method, of class ExpectedOjectInputStream.
     */
    @Test
    public void testResolveClass() throws Exception {
        List<SimplePojo> data = new ArrayList<SimplePojo>();
        data.add(new SimplePojo());

        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(mem));
        out.writeObject(data);
        out.flush();
        byte[] buf = mem.toByteArray();
        out.close();
        ByteArrayInputStream in = new ByteArrayInputStream(buf);

        ExpectedOjectInputStream instance = new ExpectedOjectInputStream(in, "java.util.ArrayList", "org.owasp.dependencycheck.utils.SimplePojo", "java.lang.Integer", "java.lang.Number");
        instance.readObject();
    }

    /**
     * Test of resolveClass method, of class ExpectedOjectInputStream.
     */
    @Test(expected = java.io.InvalidClassException.class)
    public void testResolveClassException() throws Exception {
        List<SimplePojo> data = new ArrayList<SimplePojo>();
        data.add(new SimplePojo());

        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(mem));
        out.writeObject(data);
        out.flush();
        byte[] buf = mem.toByteArray();
        out.close();
        ByteArrayInputStream in = new ByteArrayInputStream(buf);

        ExpectedOjectInputStream instance = new ExpectedOjectInputStream(in, "java.util.ArrayList", "org.owasp.dependencycheck.utils.SimplePojo");
        instance.readObject();
    }
}
