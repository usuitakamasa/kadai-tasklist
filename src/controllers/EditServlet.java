package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

        // �Y����ID�̃��b�Z�[�W1���݂̂��f�[�^�x�[�X����擾
        Tasks m = em.find(Tasks.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        // ���b�Z�[�W���ƃZ�b�V����ID�����N�G�X�g�X�R�[�v�ɓo�^
        request.setAttribute("tasks", m);
        request.setAttribute("_token", request.getSession().getId());

        // ���b�Z�[�WID���Z�b�V�����X�R�[�v�ɓo�^
        request.getSession().setAttribute("tasks_id", m.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
        rd.forward(request, response);
    }

}
