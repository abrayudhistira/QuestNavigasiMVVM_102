package com.umy.questnavigasimvvm_102.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//Deklarasi Variabel
enum class PengelolaHalaman {
    Splash,
    Mahasiswa,
    Peminatan,
    TampilKrs
}
//Pembuatan Main Program
@Composable
fun MahasiswaApp(
    modifier: Modifier = Modifier,
    mahasiswaViewModel: MahasiswaViewModel = viewModel(),
    RencanaStudyViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val mahasiswaUiState = mahasiswaViewModel.mahasiswaUiState.collectAsState().value
    val rencanaStudiUiState = RencanaStudyViewModel.krsStateUi.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = modifier.padding()
    ) {
        composable(
            route = Halaman.Splash.name
        ){
            SplashView (
                onMulaiButton = {
                    navController.navigate(Halaman.Mahasiswa.name)
                })
        }

        composable(route = Halaman.Mahasiswa.name) {
            MahasiswaFormView(
                onSubmitButton = {
                    mahasiswaViewModel.saveDataMahasiswa(it)
                    navController.navigate(Halaman.Peminatan.name)},
                onbackbuttonClicked = {navController.popBackStack()}
            )
        }

        composable(route = Halaman.Peminatan.name) {
            RencanaStudyView(
                mahasiswa = mahasiswaUiState,
                onSubmitButton = {
                    RencanaStudyViewModel.saveDataKRS(it)
                    navController.navigate(Halaman.TampilKrs.name)
                },
                onbackbuttonClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Halaman.TampilKrs.name) {
            TampilView(
                mahasiswa = mahasiswaUiState,
                krs = rencanaStudiUiState,
                onbackbuttonClicked = {
                    navController.popBackStack()
                },
                onResetButtonClicked = {
                    navController.navigate(Halaman.Splash.name)
                }
            )
        }

    }
}