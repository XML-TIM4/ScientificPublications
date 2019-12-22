package xmlteam4.Project.mappers;

public interface IMapper<TEntity, TDTO> {
    TEntity toEntity(TDTO dto) throws Exception;

    TDTO toDTO(TEntity entity);
}
