package com.mdb.example;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.resource.spi.Activation;

import com.mdb.example.entity.StudentInfo;

/**
 * Message-Driven Bean implementation class for: MDBListenerForStudentInfo
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/StudentInfoQueue") })
public class MDBListenerForStudentInfo implements MessageListener {

	/**
	 * Default constructor.
	 */
	public MDBListenerForStudentInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
				System.out
						.println("MDBListenerForStudentInfo : Received a Text Message at "
								+ new Date());
				TextMessage msg = ((TextMessage) message);
				System.out.println("Message is : " + msg.getText());

			} else if (message instanceof ObjectMessage) {
				System.out
						.println("MDBListenerForStudentInfo : Received a Object Message at "
								+ new Date());
				System.out
						.println("MDBListenerForStudentInfo : Parsing the Object Message!");

				ObjectMessage msg = (ObjectMessage) message;

				StudentInfo stuInfo = (StudentInfo) msg.getObject();
				System.out.println("Extracting Student Details!");
				System.out.println("Student Id : " + stuInfo.getStudentId());
				System.out
						.println("Student Name : " + stuInfo.getStudentName());
				System.out.println("Student Last Name : "
						+ stuInfo.getStudentLastName());
				System.out.println("Student Class : "
						+ stuInfo.getStudentClass());
				System.out.println("Student Subject are : ");
				for (String str : stuInfo.getStudentSub()) {
					System.out.println(str);
				}

			}else{
				System.out.println("Not a valid Message for Student MDBListenerForStudentInfo");
			}

		} catch (JMSException ex) {
			ex.printStackTrace();
		}

	}

}
