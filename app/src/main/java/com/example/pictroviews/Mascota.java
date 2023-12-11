package com.example.pictroviews;

import android.os.Parcel;
import android.os.Parcelable;

public class Mascota implements Parcelable {

    private int id;
    private String nombre;

    private String caracteristicas;

    public Mascota() {
    }

    public Mascota(int id, String nombre, String caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() { return caracteristicas; }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.caracteristicas);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nombre = source.readString();
        this.caracteristicas = source.readString();
    }

    protected Mascota(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.caracteristicas = in.readString();
    }

    public static final Parcelable.Creator<Mascota> CREATOR = new Parcelable.Creator<Mascota>() {
        @Override
        public Mascota createFromParcel(Parcel source) {
            return new Mascota(source);
        }

        @Override
        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }
    };

}

