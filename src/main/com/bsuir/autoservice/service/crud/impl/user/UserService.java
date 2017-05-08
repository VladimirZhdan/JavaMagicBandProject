package main.com.bsuir.autoservice.service.crud.impl.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.user;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class UserService extends AbstractServiceCrud<Integer, user> implements IUserService{

    @Inject
    public UserService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(user bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            dependencies.add(new Dependency(
                    daoUnitOfWork.getDiscountUserDao().getTableName(),
                    "user_id", bean.getId()
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getOrderDao().getTableName(),
                    "user_id", bean.getId()
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getStaffDao().getTableName(),
                    "user_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
                throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
