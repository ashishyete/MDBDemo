package com.mdb.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mdb.example.entity.StudentInfo;

/**
 * Servlet implementation class MDBClientForStudentInfo
 */
@WebServlet("/MDBClientForStudentInfo")
public class MDBClientForStudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MDBClientForStudentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final String QUEUE_LOOKUP = "queue/StudentInfoQueue";
		final String CONNECTION_FACTORY = "ConnectionFactory";

		PrintWriter out = response.getWriter();

		try {
			Context context = new InitialContext();
			QueueConnectionFactory qcf = (QueueConnectionFactory) context
					.lookup(CONNECTION_FACTORY);
			QueueConnection connection = qcf.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);

			QueueSender sender = session.createSender(queue);

			// creating a text message
			TextMessage message = session.createTextMessage();
			message.setText("Hi From Ashish : Sent at " + new Date());
			sender.send(message);
			out.println(message.getText());

			// Creting Object Message

			ObjectMessage objMsg = session.createObjectMessage();
			StudentInfo stuInfo = new StudentInfo();

			stuInfo.setStudentName("Ashish");
			stuInfo.setStudentLastName("Yete");
			stuInfo.setStudentClass("XI");
			stuInfo.setStudentId(1030);
			List<String> stuSub = new ArrayList<String>();
			stuSub.add("Chemistry");
			stuSub.add("Physics");
			stuSub.add("Biology");
			stuSub.add("Mathematics");
			stuSub.add("Social Science");
			stuInfo.setStudentSub(stuSub);

			objMsg.setObject(stuInfo);
			sender.send(objMsg);
			out.println("Sent Object Message to Queue at " + new Date());
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
