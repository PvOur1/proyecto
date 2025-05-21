package com.example.taller2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taller2.R
import com.example.taller2.activities.models.MarcaRequest
import com.example.taller2.activities.models.MotoRequest
import com.example.taller2.activities.models.MotoResponse
import com.example.taller2.activities.models.UsuarioRequest
import com.example.taller2.activities.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrearMotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crear_moto, container, false)

        val modeloEditText = view.findViewById<EditText>(R.id.etModelo)
        val tipoMotorEditText = view.findViewById<EditText>(R.id.etTipoMotor)
        val cilindrajeEditText = view.findViewById<EditText>(R.id.etCilindraje)
        val colorEditText = view.findViewById<EditText>(R.id.etColor)
        val anioEditText = view.findViewById<EditText>(R.id.etAnio)
        val precioEditText = view.findViewById<EditText>(R.id.etPrecio)
        val descripcionEditText = view.findViewById<EditText>(R.id.etDescripcion)
        val imagenEditText = view.findViewById<EditText>(R.id.etImagen)
        val kilometrajeEditText = view.findViewById<EditText>(R.id.etKilometraje)
        val combustibleEditText = view.findViewById<EditText>(R.id.etCombustible)
        val transmisionEditText = view.findViewById<EditText>(R.id.etTransmision)
        val pesoEditText = view.findViewById<EditText>(R.id.etPeso)
        val potenciaEditText = view.findViewById<EditText>(R.id.etPotencia)

        val btnGuardar = view.findViewById<Button>(R.id.btnGuardarMoto)

        btnGuardar.setOnClickListener {
            val moto = MotoRequest(
                modelo = modeloEditText.text.toString(),
                tipo_motor = tipoMotorEditText.text.toString(),
                cilindraje = cilindrajeEditText.text.toString(),
                color = colorEditText.text.toString(),
                año = anioEditText.text.toString(),
                precio = precioEditText.text.toString(),
                descripcion = descripcionEditText.text.toString(),
                images = imagenEditText.text.toString(),
                kilometraje = kilometrajeEditText.text.toString(),
                combustible = combustibleEditText.text.toString(),
                transmision = transmisionEditText.text.toString(),
                peso = pesoEditText.text.toString(),
                potencia = potenciaEditText.text.toString(),
                disponible = true,
                marca = MarcaRequest(id = 1),
                usuario = UsuarioRequest(id = 1)
            )

            val token = "Bearer TU_TOKEN_AQUI" // Reemplázalo por el token real

            RetrofitClient.instance.postMotos(token, moto)
                .enqueue(object : Callback<MotoResponse> {
                    override fun onResponse(call: Call<MotoResponse>, response: Response<MotoResponse>) {
                        if (response.isSuccessful) {
                            Toast.makeText(requireContext(), "Moto registrada exitosamente", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Error al registrar: ${response.code()}", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<MotoResponse>, t: Throwable) {
                        Log.e("CrearMotoFragment", "Error: ${t.message}")
                        Toast.makeText(requireContext(), "Error de red", Toast.LENGTH_SHORT).show()
                    }
                })

        }

        return view
    }
}
