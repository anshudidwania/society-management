package ad.society.apartmentmanagement.transformer;

public interface ObjectTransformer<E,D> {
	D toValueObject(E entity); 
	E toEntity(D valueObject);
}
