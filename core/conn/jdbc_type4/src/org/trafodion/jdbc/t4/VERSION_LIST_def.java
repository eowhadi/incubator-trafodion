// @@@ START COPYRIGHT @@@
//
// (C) Copyright 2003-2014 Hewlett-Packard Development Company, L.P.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
// @@@ END COPYRIGHT @@@

package org.trafodion.jdbc.t4;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.UnsupportedCharsetException;

class VERSION_LIST_def {
	VERSION_def[] list;

	int sizeOf() { // since VERSION_def is a constant size we can just call the
		// sizeOf() once
		return VERSION_def.sizeOf() * list.length + TRANSPORT.size_int;
	}

	void insertIntoByteArray(LogicalByteArray buf) {
		buf.insertInt(list.length);

		for (int i = 0; i < list.length; i++) {
			list[i].insertIntoByteArray(buf);
		}
	}
	
	int sizeOfChar() {
		return list.length * VERSION_def.sizeOfChar() + 10;
	}
	
	void insertIntoByteArrayChar(LogicalByteArray buf, InterfaceConnection ic) throws CharacterCodingException, UnsupportedCharsetException{
		buf.insertFixedString(ic.encodeString("" + list.length, 1), 10);

		for (int i = 0; i < list.length; i++) {
			list[i].insertIntoByteArrayChar(buf, ic);
		}
	}

	void extractFromByteArray(LogicalByteArray buf) {
		int len = buf.extractInt();

		list = new VERSION_def[len];

		for (int i = 0; i < list.length; i++) {
			list[i] = new VERSION_def();
			list[i].extractFromByteArray(buf);
		}
	}
}
