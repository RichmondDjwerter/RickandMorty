package com.richmondprojects.rickandmorty.data.repository

import com.richmondprojects.rickandmorty.data.local.DataBase
import com.richmondprojects.rickandmorty.data.mappers.toResults
import com.richmondprojects.rickandmorty.data.mappers.toResultsDomain
import com.richmondprojects.rickandmorty.data.mappers.toResultsEntity
import com.richmondprojects.rickandmorty.data.remote.Api
import com.richmondprojects.rickandmorty.domain.model.Results
import com.richmondprojects.rickandmorty.domain.repository.Repository
import com.richmondprojects.rickandmorty.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: Api,
    db: DataBase
) : Repository {

    private val dao = db.dao

    override suspend fun getCharacters(
        page: Int,
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Results>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCache = dao.searchCharacters(query)
            emit((Resource.Success(localCache.map { it.toResults() })))

            val isDBEmpty = localCache.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDBEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val response = try {
                val result = api.getCharacters(page)
                Resource.Success(result.results.map { it.toResultsDomain() })
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.localizedMessage))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.message()))
                null
            }
            response?.data?.let { result ->
                dao.insertCharacters(result.map { it.toResultsEntity() })
                emit(
                    Resource.Success(data = dao
                        .searchCharacters("")
                        .map { it.toResults() })
                )
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getCharacterDetails(id: Int): Resource<Results> {
        return try {
            Resource.Success(api.getCharacterInfo(id).toResultsDomain())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage)
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(e.message())
        }
    }
}