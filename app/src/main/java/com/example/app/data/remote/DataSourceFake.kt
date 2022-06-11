package com.example.app.data.remote

import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TrackModel
import com.github.krottv.tmstemp.domain.Tracks
import okhttp3.ResponseBody
import retrofit2.Response

class DataSourceFake : RemoteDataSourceRetrofit {

    override suspend fun getAlbums(): List<AlbumModel> {
        val model = AlbumModel(
            0,
            "https://inspiry-2ee60.web.app/music/images/itunes/hip_hop.jpg",
            "Some Text",
            1
        )

        val mutableListOf = ArrayList<AlbumModel>(10)
        for (i in 0..10) {
            mutableListOf.add(model.copy(name = "Some Text $i"))
        }

        return mutableListOf
    }

    override suspend fun getTracks(albumId: Long): Tracks {

        return Tracks(
            AlbumModel(
                0,
                "https://inspiry-2ee60.web.app/music/images/itunes/hip_hop.jpg",
                "Some Text",
                1
            ),
            arrayListOf(
                TrackModel(
                    1,
                    "sadsaf",
                    "https://inspiry-2ee60.web.app/music/images/itunes/hip_hop.jpg",
                    "sadas",
                    "https://sefon.pro/api/mp3_download/direct/715434/3vUCALS9wFZWtyA8yzt4jxmjH2dSTifxrLtB_1f9WIUvts6N21S_rB0s5JQFHIy4wxfYW4vxH7TDxQ-Xes3IoUJuMkSoJIv9LfSx7P69GbK_lXqPHQSga-748euDWlblPrqtwbKbtSTeOQn_2O_CLx-C7dRia7Efwa9HH48/",
                    0
                )
            )
        )
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {
        TODO("Not yet implemented")
    }
}