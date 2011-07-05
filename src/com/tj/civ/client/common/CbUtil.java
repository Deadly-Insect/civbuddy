/*
 * CivBuddy - A Civilization Tactics Guide
 * Copyright (c) 2011 Thomas Jensen
 * $Id$
 * Date created: 05.03.2011
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License Version 2 as published by the Free
 * Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.tj.civ.client.common;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

import com.tj.civ.client.model.jso.CbGameJSO;


/**
 * Common utility methods used throughout the code.
 *
 * @author Thomas Jensen
 */
public final class CbUtil
{
    /**
     * Generate a unique ID using Robert Kieffer's JavaScript code from
     * <tt>uuid.cache.js</tt>.
     * @return a new UUID
     */
    public static native String getUuid()
    /*-{
        return $wnd.uuid();
    }-*/;



    /**
     * Factory method.
     * @param <J> type of {@link JavaScriptObject} returned by this method
     * @param pJson the JSON representation of a {@link CbGameJSO}
     * @return a new instance, or <code>null</code> if the instance could not be created
     */
    public static <J extends JavaScriptObject> J createFromJson(final String pJson)
    {
        J result = null;
        JSONValue v = JSONParser.parseStrict(pJson);
        if (v != null) {
            JSONObject obj = v.isObject();
            if (obj != null) {
                result = obj.getJavaScriptObject().cast();
            }
        }
        return result;
    }



    /**
     * Set the browser title, postfixed with the app name.
     * @param pTitleText text to set, <code>null</code> sets no special title
     */
    public static void setBrowserTitle(final String pTitleText)
    {
        String msg = CbConstants.APPNAME;
        if (pTitleText != null) {
            msg = pTitleText + " - " + msg; //$NON-NLS-1$
        }
        Window.setTitle(msg);
    }



    /**
     * Return the given class' simple name, as would Class.getSimpleName(), which
     * is unavailable in GWT.
     * @param pClazz the class
     * @return the class' simple name
     */
    public static String simpleName(final Class<?> pClazz)
    {
        String result = null;
        if (pClazz != null) {
            String name = pClazz.getName();
            result = name.substring(name.lastIndexOf('.') + 1);
        }
        return result;
    }



    /**
     * Determine the maximum of a number of ints.
     * @param pValues any number of int values
     * @return the greatest of the given values
     * @see Math#max(int, int)
     */
    private int max(final int... pValues)
    {
        int result = Integer.MIN_VALUE;
        if (pValues != null) {
            for (int p : pValues) {
                if (p > result) {
                    result = p;
                }
            }
        }
        return result;
    }



    private CbUtil()
    {
        super();
    }
}
