package slack;

import java.util.List;

import javax.jms.Destination;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
DurableChat application

A basic JMS Application that uses:
    - Publish and Subscribe
    - Durable Subsciptions
    - Persistent Messages

This sample publishes and subscribes to a specified topic.
Text you enter is published to the topic with the user name.
The message will persist for ten minutes if the subscriber is not available.
If the subscriber reconnects within that time, the message is delivered.

Usage:
  java DurableChat -b <broker:port> -u <username> -p <password>
      -b broker:port points to your message broker
                     Default: tcp://localhost:61616
      -u username    must be unique (but is not checked)
      -p password    password for user (not checked)

Suggested demonstration:
  - In separate console windows, start instances of the application
    under unique user names.For example:
       java DurableChat -b tcp://localhost:61616 -u ACCOUNTING
       java DurableChat -b tcp://localhost:61616 -u LEGAL
  - Enter text and then press Enter to publish the message.
  - See messages appear under the various user names as you
    enter messages in each console window.
  - Stop a session by pressing CTRL+C in its console window.
  - Keep sending messages in other sessions.
  - Restart the subscriber username session that was stopped.
  - Note that the "missed" messages are still available if the restart is
    within thirty minutes.

*/
import org.apache.activemq.*;
import org.apache.activemq.command.ActiveMQTopic;


public class DurableChat     // to handle message subscriptions
{
    private static final String APP_TOPIC = "jms.samples.durablechat";
    private static final String DEFAULT_BROKER_NAME = "tcp://localhost:61616";
    private static final String DEFAULT_PASSWORD = "password";
    private static final long   MESSAGE_LIFESPAN = 1800000; //30 minutes

    private javax.jms.Connection connection = null;
    private javax.jms.Session pubSession = null;
    private javax.jms.Session subSession = null;


    public void DurableChatter(String username, String password, String group)
    {
        javax.jms.MessageConsumer subscriber = null;
        javax.jms.Topic topic = null;

        //Create a connection:
        try{
            javax.jms.ConnectionFactory factory;
            factory = new ActiveMQConnectionFactory(username, password, DEFAULT_BROKER_NAME);
            connection = factory.createConnection (username, password);

            //Durable Subscriptions are indexed by username, clientID and subscription name
            //It is a good practice to set the clientID:
            connection.setClientID(username);
            pubSession = connection.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
            subSession = connection.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
        }
        catch (javax.jms.JMSException jmse){
            System.err.println ("Error: Cannot connect to Broker - " + DEFAULT_BROKER_NAME);
            jmse.printStackTrace();
            System.exit(1);
        }

        //Create Publisher and Durable Subscriber:
        try{
            topic = pubSession.createTopic(group);
            subscriber = subSession.createDurableSubscriber(topic, username+group);
            System.out.println(username + " subscribed " + topic);
            connection.close();
        }
        catch (javax.jms.JMSException jmse){
            System.out.println("Error: connection not started.");
            jmse.printStackTrace();
            System.exit(1);
        }

        
    }

    /** Message Handler**/
    public void onMessage( javax.jms.Message aMessage)
    {
        try
        {
            // Cast the message as a text message.
            javax.jms.TextMessage textMessage = (javax.jms.TextMessage) aMessage;

            // This handler reads a single String from the
            // message and prints it to the standard output.
            try
            {
                String string = textMessage.getText();
                System.out.println( string );
            }
            catch (javax.jms.JMSException jmse)
            {
                jmse.printStackTrace();
            }
        }
        catch (java.lang.RuntimeException rte)
        {
            rte.printStackTrace();
        }
    }



    /** Cleanup resources cleanly and exit. */
    private void exit()
    {
        try
        {
            connection.close();
        }
        catch (javax.jms.JMSException jmse)
        {
            jmse.printStackTrace();
        }

        System.exit(0);
    }

    //
    // NOTE: the remainder of this sample deals with reading arguments
    // and does not utilize any JMS classes or code.
    //

}
