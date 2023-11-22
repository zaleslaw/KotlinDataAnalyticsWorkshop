const val URL = "jdbc:mariadb://localhost:3307/imdb"
const val USER_NAME = "root"
const val PASSWORD = "pass"
const val TABLE_NAME = "actors"
const val TARANTINO_FILMS_SQL_QUERY = "select name, year, rank,\n" +
        "GROUP_CONCAT (genre) as \"genres\"\n" +
        "from movies join movies_directors on  movie_id = movies.id\n" +
        "     join directors on directors.id=director_id left join movies_genres on movies.id = movies_genres.movie_id \n" +
        "where directors.first_name = \"Quentin\" and directors.last_name = \"Tarantino\"\n" +
        "group by name, year, rank\n" +
        "order by year"