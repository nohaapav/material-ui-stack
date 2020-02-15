module.exports = {
  entry: {
    'material-ui-stack': './main.js'
  },
  output: {
    filename: '[name].inc.js'
  },
  externals: {
    'react': 'React',
    'react-dom': 'ReactDOM'
  }
};
