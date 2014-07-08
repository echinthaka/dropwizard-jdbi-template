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

package org.chinthaka.dropwizard.api;

import org.chinthaka.dropwizard.api.beans.User;

import javax.ws.rs.*;

/**
 * User: Eran Withana
 * Date: 7/8/14
 */
@Path("/user")
public interface UserResource {

    @PUT
    public void addUser(final User user);

    @GET
    @Path("/{userId}")
    public User getUserById(@PathParam("userId") final String userId);

    @DELETE
    @Path("/{userId}")
    @Produces("application/json")
    public void removeUser(@PathParam("userId") final String userId);
}
