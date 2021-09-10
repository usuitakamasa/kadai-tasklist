package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Tasks;

public class TasksValidator {
    // �o���f�[�V���������s����
    public static List<String> validate(Tasks m) {
        List<String> errors = new ArrayList<String>();

        String content_error = validateContent(m.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    // �^�X�N�̕K�{���̓`�F�b�N
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "�^�X�N����͂��Ă��������B";
        }

        return "";
    }
}