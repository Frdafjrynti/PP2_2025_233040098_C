package id.ac.unpas.pp2_c_233040098.TugasModul10;

import id.ac.unpas.pp2_c_233040098.TugasModul10.View.MahasiswaView;
import id.ac.unpas.pp2_c_233040098.TugasModul10.Controller.MahasiswaController;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            MahasiswaController controller = new MahasiswaController(view);
            view.setVisible(true);
        });
    }
}
