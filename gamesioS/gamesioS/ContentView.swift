import SwiftUI
import shared
import URLImage

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        NavigationView {
            listView()
                .navigationBarTitle("Games")
        }
    }
    
    private func listView() -> AnyView {
        
        switch viewModel.response {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let response):
            let games = response.games
            return AnyView( ScrollView {
                VStack {
                    ForEach(0 ..< games.count){ index in
                        GameRow(game: games[index])
                    }
                }
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

struct GameRow: View {
    var game: Game
    
    var body: some View {
        VStack {
            HStack(alignment: .top) {
                GameBackground(url: game.background)
                    .overlay(
                        VStack(alignment: .leading) {
                            Text(String(format: "%.1f", game.rating))
                                .font(.system(size : 45))
                                .foregroundColor(Color.white)
                                .bold()
                                .padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 0))
                            Text("Rating")
                                .font(.system(size : 13))
                                .foregroundColor(Color.white)
                                .bold()
                                .padding(10)
                        }
                        , alignment: .bottomLeading)
                    .padding(EdgeInsets(top: 10, leading: 25, bottom: 0, trailing: 0))
                
                VStack(alignment: .leading) {
                    Text(game.name)
                        .fixedSize(horizontal: false, vertical: true)
                        .font(.system(size: 25))
                        .padding(20)
                        .lineLimit(2)
                    
                    Text(game.released)
                    .font(.system(size: 15))
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 0))
                }
                
                Spacer()
            }
            
            Divider()
        }
    }
}

struct GameBackground: View {
    var url : String
    
    var body: some View {
        URLImage(URL(string: url)!,
                 delay: 0.25,
                 processors: [ Resize(size: CGSize(width: 100.0, height: 100.0), scale: UIScreen.main.scale) ],
                 content:  {
                    $0.image
                        .resizable().frame(width: 100, height: 180, alignment: .center)
                        .overlay(Rectangle().background(Color.black).opacity(0.3))
            }
        )
    }
}

extension ContentView {
    enum ResponseStatus {
        case loading
        case result(GameResponse)
        case error(String)
    }
    
    class ViewModel: ObservableObject {
        let gamesSdk: GamesSDK
        
        @Published var response = ResponseStatus.loading
        
        init(sdk: GamesSDK) {
            self.gamesSdk = sdk
            self.loadAllGames()
        }
        
        func loadAllGames(){
            self.response = .loading
            
            gamesSdk.getAllGames { response, error in
                if let response = response {
                    self.response = .result(response)
                } else {
                    self.response = .error(error?.localizedDescription ?? "error")
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
