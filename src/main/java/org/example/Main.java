package org.example;
import org.example.classes.*;
import org.example.interfaces.*;

public class Main {
    public static void main(String[] args) {
        Dayer dayer = new Dayer();
        Pebody pebody = new Pebody();
        Wilmart wilmart = new Wilmart();
        Ashton ashton = new Ashton();
        Author author = new Author("me");
        Stalagmite sg = new Stalagmite("Сталагмиты","высокая прочность");
        LimeStone ls = new LimeStone("известняк","");
        GeologicalLayer gl = new GeologicalLayer("Геологические пласты","","периода позднего мела или раннего эоцена");
        Dog dog = new Dog("Собаки",true);
        Creature creature = new Creature("особи");
        Elders elders = new Elders();
        creature.resemble("существ из древней мифологии");
        creature.speculate("жили вне Антарктиды");
        dayer.read("Некрономикон");
        pebody.read("Некрономикон");
        dayer.watch("рисунки Кларка Эштона");
        pebody.watch("рисунки Кларка Эштона");
        ashton.draw("рисунки","Некрономиконом");
        pebody.understand("меня когда");
        author.talk("Старцах");
        dayer.understand("меня когда");
        author.talk("Старцах");
        elders.speculate("породили жизнь");
        elders.speculate("выглядят как морская звезда");
        wilmart.write("чудовищах из фольклора");
        gl.speculate(gl.getAgePeriod());
        sg.hang("над "+gl.getGeoType());
        ls.keepIntact("все");
        author.task("отправить образцы на базу");
        dog.bark("хрипят от лая");
    }
}