package dao;

import model.Barrage;

import java.util.List;

public interface BarrageDao {

    List<Barrage> getBarrageListByValuePath(String valuePath);

    void save(Barrage barrage);

    void deleteBarrageById(int id);

}
