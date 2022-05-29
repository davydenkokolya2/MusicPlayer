package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.AlbumModel

class DataSourceFake {

    fun getITunesAlbums(): List<AlbumModel>{
        val model = AlbumModel(0,
        "https://inspiry-2ee60.web.app/music/images/itunes/hip_hop.jpg",
        "Some Text",
        10)

        val mutableListOf = ArrayList<AlbumModel>(10)
        for (i in 0..10){
            mutableListOf.add(model.copy(name = "Some Text $i"))
        }

        return mutableListOf
    }
}