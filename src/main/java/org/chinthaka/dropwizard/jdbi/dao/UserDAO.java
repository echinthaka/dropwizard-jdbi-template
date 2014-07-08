/*
 * Copyright [2014] [Eran C. Withana]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.chinthaka.dropwizard.jdbi.dao;

import org.chinthaka.dropwizard.api.beans.User;
import org.chinthaka.dropwizard.jdbi.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * User: Eran Withana
 * Date: 7/8/14
 */
@RegisterMapper(UserMapper.class)
public interface UserDAO {

    // NOTE: User is a reserved keyword in Derby
    public static final String USER_TABLE = "AppUser";

    @SqlUpdate("create table " + USER_TABLE + " (id varchar(100), name varchar(100), city varchar(100))")
    void createUserTable();

    @SqlUpdate("insert into " + USER_TABLE + " (id, name, city) values (:id, :name, :city)")
    void insert(@BindBean final User user);

    @SqlQuery("select * from " + USER_TABLE + " where id = :id")
    User findUserById(@Bind("id") String id);

    @SqlUpdate("delete from " + USER_TABLE + " where id = :it")
    void removeUser(@Bind String userId);
}
