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

package org.chinthaka.dropwizard.resources;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.chinthaka.dropwizard.MyApplication;
import org.chinthaka.dropwizard.MyApplicationConfiguration;
import org.chinthaka.dropwizard.api.beans.User;
import org.junit.ClassRule;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class UserResourceTest {

    @ClassRule
    public static final DropwizardAppRule<MyApplicationConfiguration> RULE =
            new DropwizardAppRule<>(MyApplication.class, "myapp.yaml");

    private final User sampleUser = new User("1234", "Example User", "Ambalangoda");

    @org.junit.Before
    public void setUp() throws Exception {


    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testAddUser() throws Exception {
        Client client = new Client();

        // add a user
        final int localPort = RULE.getLocalPort();
        ClientResponse response = client.resource(
                String.format("http://localhost:%d/user", localPort)).type(MediaType.APPLICATION_JSON_TYPE)
                .put(ClientResponse.class, sampleUser);

        // make sure server returns the proper status code
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));

        // now lets try to retrieve it
        response =
                client.resource(String.format("http://localhost:%d/user/%s", localPort, sampleUser.getId()))
                        .accept(MediaType.APPLICATION_JSON_TYPE)
                        .get(ClientResponse.class);

        // first check the server response code
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));

        // validate the returned user vs the inserted one
        final User user = response.getEntity(User.class);
        assertEquals(sampleUser.getId(), user.getId());
        assertEquals(sampleUser.getName(), user.getName());
        assertEquals(sampleUser.getCity(), user.getCity());

        // now lets delete the user.
        response =
                client.resource(String.format("http://localhost:%d/user/%s", localPort, sampleUser.getId()))
                        .delete(ClientResponse.class);

        // check the server response code
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));

        // lets try to retrieve it again
        response =
                client.resource(String.format("http://localhost:%d/user/%s", localPort, sampleUser.getId()))
                        .get(ClientResponse.class);

        // we shouldn't have gotten anything
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));

    }
}