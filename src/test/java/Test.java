
import com.n2.sprintburst.service.SanPhamChiTietService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Mtt
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(SanPhamChiTietService.filterByKeyword("m", 0, 999999999).size());
    }
}
