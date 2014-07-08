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

package org.chinthaka.dropwizard.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.chinthaka.dropwizard.api.UserResource;
import org.chinthaka.dropwizard.api.beans.User;

import javax.ws.rs.core.MediaType;

/**
 * User: Eran Withana
 * Date: 7/8/14
 */
public class UserResourceClient implements UserResource {

    private final Client client = new Client();
    private final String resourceUrl;

    public UserResourceClient(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Override
    public void addUser(User user) {
        client.resource(
                resourceUrl).type(MediaType.APPLICATION_JSON_TYPE)
                .put(ClientResponse.class, user);
    }

    @Override
    public User getUserById(String userId) {
        final ClientResponse clientResponse = client.resource(
                resourceUrl + "/" + userId).type(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
        return clientResponse.getEntity(User.class);

    }

    @Override
    public void removeUser(String userId) {
        client.resource(
                resourceUrl + "/" + userId).type(MediaType.APPLICATION_JSON_TYPE)
                .delete(ClientResponse.class);
    }
}
