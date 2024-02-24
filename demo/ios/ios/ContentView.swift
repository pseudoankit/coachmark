//
//  ContentView.swift
//  ios
//
//  Created by Ankit Kumar on 24/02/24.
//

import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        IosMainKt.controller()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
