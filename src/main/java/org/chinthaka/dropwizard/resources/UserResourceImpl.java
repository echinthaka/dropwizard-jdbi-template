/*
 * Copyright 2014 Eran C. Withana
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

package org.chinthaka.dropwizard.resources;

import org.chinthaka.dropwizard.api.UserResource;
import org.chinthaka.dropwizard.api.beans.User;
import org.chinthaka.dropwizard.jdbi.dao.UserDAO;

/**
 * User: Eran Withana
 * Date: 7/8/14
 */

public class UserResourceImpl implements UserResource {

    private final UserDAO userDAO;

    public UserResourceImpl(UserDAO dao) {
        this.userDAO = dao;
    }

    public void addUser(final User user) {
        this.userDAO.insert(user);
    }

    public User getUserById(final String userId) {
        return this.userDAO.findUserById(userId);
    }

    public void removeUser(final String userId) {
        try {
            this.userDAO.removeUser(userId);
        } catch (Exception e) {
            // no user to delete
        }
    }
}
