package dao.impl;

import dao.BarrageDao;
import dao.Dao;
import model.Barrage;

import java.util.List;

public class BarrageDaoImpl extends Dao<Barrage> implements BarrageDao {
    @Override
    public List<Barrage> getBarrageListByValuePath(String valuePath) {
        return null;
    }

    @Override
    public void save(Barrage barrage) {

    }

    @Override
    public void deleteBarrageById(int id) {

    }
}
