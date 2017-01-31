package br.com.mobilemind.api.test;

/*
 * #%L
 * Mobile Mind - Utils
 * %%
 * Copyright (C) 2012 - 2013 Mobile Mind Empresa de Tecnologia
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import br.com.mobilemind.api.security.key.SHA256Encryptor;
import br.com.mobilemind.api.utils.ClassUtil;
import junit.framework.Assert;
import org.junit.Test;



/**
 *
 * @author Ricardo Bocchi
 */
public class ClassUtilTestCase {

    interface A {
    }

    interface B {
    }

    class C {
    }

    class D extends C implements A, B {
    }

    @Test
    public void testIsAssignableFrom() {

        Assert.assertTrue(ClassUtil.isAssignableFrom(D.class, A.class));
        Assert.assertTrue(ClassUtil.isAssignableFrom(D.class, B.class));
        Assert.assertTrue(ClassUtil.isAssignableFrom(D.class, C.class));

    }

    @Test
    public void test() {

        byte cript1[] = null, cript2[] = null;

        try {
            cript1 = SHA256Encryptor.digest("ricardo".getBytes());
        } catch (Exception e) {
        }

        try {
            cript2 = SHA256Encryptor.digest("ricardo".getBytes());
        } catch (Exception e) {
        }
        
        String velue1 = SHA256Encryptor.byteArrayToHexString(cript1);
        String velue2 = SHA256Encryptor.byteArrayToHexString(cript2);
        
        Assert.assertEquals(velue1, velue2);
    }
}
