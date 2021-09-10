package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // �Z�b�V�����X�R�[�v���烁�b�Z�[�W��ID���擾���ĊY����ID�̃^�X�N1���݂̂��f�[�^�x�[�X����擾
            Tasks m = em.find(Tasks.class, (Integer)(request.getSession().getAttribute("tasks_id")));

            // �t�H�[���̓��e���e�t�B�[���h�ɏ㏑��
            String content = request.getParameter("content");
            m.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setUpdated_at(currentTime);       // �X�V�����̂ݏ㏑��

            // �f�[�^�x�[�X���X�V
            em.getTransaction().begin();
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "�X�V���������܂����B");
            em.close();

            // �Z�b�V�����X�R�[�v��̕s�v�ɂȂ����f�[�^���폜
            request.getSession().removeAttribute("tasks_id");

            // index�y�[�W�փ��_�C���N�g
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
