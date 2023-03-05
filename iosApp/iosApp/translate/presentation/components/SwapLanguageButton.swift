//
//  SwapLanguageButton.swift
//  iosApp
//
//  Created by Atitienei Daniel on 04.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SwapLanguageButton: View {
    var onClick: () -> Void
 
    var body: some View {
        Button(action: onClick) {
            Image(uiImage: UIImage(named:"swap_languages")!)
                .padding()
                .background(Color.primaryColor)
                .clipShape(Circle())
        }
    }
}

struct SwapLanguageButton_Previews: PreviewProvider {
    static var previews: some View {
        SwapLanguageButton(
            onClick: {}
        )
    }
}
