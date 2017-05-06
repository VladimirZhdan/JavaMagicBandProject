package main.com.bsuir.autoservice.service.crud.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.order;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

public class OrderService extends AbstractServiceCrud<Integer,order> implements IOrderService {

    @Inject
    public OrderService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<String> getDependencyTablesNames(){
        List<String> dependencyTableNames = new ArrayList<>();
        dependencyTableNames.add(daoUnitOfWork.getOrderedServiceDao().getTableName());
        dependencyTableNames.add(daoUnitOfWork.getOrderSparePartDao().getTableName());
        dependencyTableNames.add(daoUnitOfWork.getNotificationDao().getTableName());
        return dependencyTableNames;
    }

    @Override
    public Map<String, Dependency> readDependencies(order bean) throws ServiceException {
        try {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();
            dependencies.put(
                    daoUnitOfWork.getOrderedServiceDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getOrderedServiceDao(), "order_id", bean.getId())
            );
            dependencies.put(
                    daoUnitOfWork.getOrderSparePartDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getOrderSparePartDao(), "order_id", bean.getId())
            );
            dependencies.put(
                    daoUnitOfWork.getNotificationDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getNotificationDao(), "order_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}