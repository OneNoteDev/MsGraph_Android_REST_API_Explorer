/*
*  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license. See full license at the bottom of this file.
*/
package com.microsoft.o365_android_onenote_rest.model;

import com.microsoft.o365_android_onenote_rest.application.SnippetApp;

import static com.microsoft.o365_android_onenote_rest.R.string.NotesReadWrite;
import static com.microsoft.o365_android_onenote_rest.R.string.NotesReadWriteAll;
import static com.microsoft.o365_android_onenote_rest.R.string.offline_access_permission;
import static com.microsoft.o365_android_onenote_rest.R.string.open_id;

public class Scope {

    public static final String DELIM = "\n---\n";

    public enum wl {
        openid(open_id),
        offline_access(offline_access_permission);

        public final String mDescription;

        wl(int desc) {
            mDescription = getString(desc);
        }

        public String getScope() {
            return name();
        }

        @Override
        public String toString() {
            return getScope() + DELIM + mDescription;
        }
    }

    public enum office {
        Notes_ReadWrite(NotesReadWrite),
        Notes_ReadWrite_All(NotesReadWriteAll);

        public final String mDescription;

        office(int desc) {
            mDescription = getString(desc);
        }

        public String getScope() {
            return name().replace("_", ".");
        }

        @Override
        public String toString() {
            return getScope() + DELIM + mDescription;
        }
    }

    private static String getString(int res) {
        return SnippetApp.getApp().getString(res);
    }
}
// *********************************************************
//
// MsGraph_Android_REST_API_Explorer, https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer
//
// Copyright (c) Microsoft Corporation
// All rights reserved.
//
// MIT License:
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
// *********************************************************
