package dao;

import model.Barrage;

import java.util.List;

public interface BarrageDao {

    List<Barrage> getBarrageListByValuePath(String videoPath);

    void save(Barrage barrage, String videoPath);

    void deleteBarrageById(int id);

}
