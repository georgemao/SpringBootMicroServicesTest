package demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String>{
	public List<Car> findByMake(String make);
    public List<Car> findByColor(String color);
}

