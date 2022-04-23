package me.sathish.witrafficalerts.corealert.repo;

import me.sathish.witrafficalerts.corealert.bean.TrafficAlert;
import org.springframework.data.repository.CrudRepository;

public interface TrafficAlertRepo extends CrudRepository<TrafficAlert,Long> {
}
