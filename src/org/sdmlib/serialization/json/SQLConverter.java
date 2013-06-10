package org.sdmlib.serialization.json;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLConverter {
	public static JsonObject sqlToJsonObject(ResultSet sqlSet)
			throws SQLException {
		JsonObject json = new JsonObject();
		ResultSetMetaData metaData = sqlSet.getMetaData();
		for (int z = 0; z < metaData.getColumnCount(); z++) {
			String columnName = metaData.getColumnName(z);
			json.put(columnName, sqlSet.getObject(z+1));
		}
		return json;
	}

	public static JsonObject sqlToJson(ResultSet sqlSet, String clazz)
			throws SQLException {
		JsonObject msg = new JsonObject(JsonIdMap.CLASS, clazz);
		msg.put(JsonIdMap.JSON_PROPS, sqlToJsonObject(sqlSet));
		return msg;
	}

	public static JsonObject sqlToJson(ResultSet sqlSet, String clazz, String id)
			throws SQLException {
		JsonObject msg = new JsonObject(JsonIdMap.CLASS, clazz, JsonIdMap.ID,
				id);
		msg.put(JsonIdMap.JSON_PROPS, sqlToJsonObject(sqlSet));
		return msg;
	}

	public static JsonArray sqlToJsonArray(ResultSet sqlSet, String clazz)
			throws SQLException {
		JsonArray jsonlist = new JsonArray();
		while (!sqlSet.isAfterLast() && sqlSet.isClosed()) {
			jsonlist.add(sqlToJson(sqlSet, clazz));
			sqlSet.next();
		}
		return jsonlist;
	}
}
